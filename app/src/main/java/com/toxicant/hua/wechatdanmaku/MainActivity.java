package com.toxicant.hua.wechatdanmaku;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import pack.HeartBeatThread;
import pack.WaitScanAndLoginThread;
import pack.WeChatClass;

public class MainActivity extends AppCompatActivity {

    private View danmakuLayout;
    private IDanmakuView mDanmakuView;
    private DanmakuContext mContext;
    private BaseDanmakuParser mParser;
    private WindowManager mWindowManager;
    private ImageView qrcodeView;
    private TextView tipView;
    private Button btnStop;

    private WeChatClass weChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDanmakuView();
    }


    private void initView(){
        qrcodeView= (ImageView) findViewById(R.id.iv_qrcode);
        tipView= (TextView) findViewById(R.id.tv_tip);
        btnStop= (Button) findViewById(R.id.stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //floatView
        mWindowManager= (WindowManager) getApplication().getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        wmParams.type= WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        wmParams.format= PixelFormat.RGBA_8888;
        wmParams.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        wmParams.x = 0;
        wmParams.y = 0;
        wmParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height= WindowManager.LayoutParams.MATCH_PARENT;

        LayoutInflater inflater= LayoutInflater.from(getApplication());
        danmakuLayout=inflater.inflate(R.layout.danmaku_layout, null);
        mDanmakuView= (IDanmakuView) danmakuLayout.findViewById(R.id.danmaku);
        mWindowManager.addView(danmakuLayout, wmParams);

        //微信相关的初始化
        weChat=new WeChatClass();
        weChat.setmQrCodeListener(new WeChatClass.OnLoadQrCodeListener() {
            @Override
            public void onLoadSuccess(byte[] imageBytes) {
                final Bitmap bitmap=BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        qrcodeView.setImageBitmap(bitmap);
                    }
                });
            }
        });
        weChat.setmScanListener(new WaitScanAndLoginThread.OnScanListener() {
            @Override
            public void onScan() {//已经扫描
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tipView.setText("已经扫描，请确认登陆");
                    }
                });

            }

            @Override
            public void onSure() {//已经确认登陆
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tipView.setText("已经确认登陆,正在初始化，请稍后");
                    }
                });
            }
        });
        weChat.setmNewMsgListener(new HeartBeatThread.OnNewMsgListener() {
            @Override
            public void onNewMsg(final String text) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addDanmaku(text);
                    }
                });
            }

            @Override
            public void startBeat() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tipView.setText("心跳线程开启");
                    }
                });
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                weChat.gogogo();
            }
        }).start();
    }
    /**
     * 设置弹幕，初始化。
     */
    private void initDanmakuView(){
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
//            .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//            .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
        if (mDanmakuView != null) {
            mParser=new BaseDanmakuParser() {
                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
            mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void drawingFinished() {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
//                    Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
                }

                @Override
                public void prepared() {
                    mDanmakuView.start();
                }
            });
            mDanmakuView.prepare(mParser, mContext);
            mDanmakuView.showFPS(true);
            mDanmakuView.enableDanmakuDrawingCache(true);
        }

    }//init

    private void addDanmaku(String text) {
        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || mDanmakuView == null) {
            return;
        }
        // for(int i=0;i<100;i++){
        // }
        danmaku.text = text;
        danmaku.padding = 5;
        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = true;
        danmaku.time = mDanmakuView.getCurrentTime() + 1200;
        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.WHITE;
        danmaku.textShadowColor = 0;
        // danmaku.underlineColor = Color.GREEN;
        danmaku.borderColor = 0;
        mDanmakuView.addDanmaku(danmaku);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
        mWindowManager.removeView(danmakuLayout);
    }

}
