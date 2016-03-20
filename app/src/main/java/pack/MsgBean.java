package pack;

import java.util.List;

/**
 * Created by hua on 2016/3/19.
 */
public class MsgBean {
    /**
     * MsgId : 8820250664544778582
     * FromUserName : @56d03149f1b5527c892fea59983532586fb8139c9b0307e2eb4d82f4b6dece2c
     * ToUserName : @8bdd60651b561cfe3403df660d0b4f96
     * MsgType : 1
     * Content : 12345678910
     * Status : 3
     * ImgStatus : 1
     * CreateTime : 1458318602
     * VoiceLength : 0
     * PlayLength : 0
     * FileName :
     * FileSize :
     * MediaId :
     * Url :
     * AppMsgType : 0
     * StatusNotifyCode : 0
     * StatusNotifyUserName :
     * RecommendInfo : {"UserName":"","NickName":"","QQNum":0,"Province":"","City":"","Content":"","Signature":"","Alias":"","Scene":0,"VerifyFlag":0,"AttrStatus":0,"Sex":0,"Ticket":"","OpCode":0}
     * ForwardFlag : 0
     * AppInfo : {"AppID":"","Type":0}
     * HasProductId : 0
     * Ticket :
     * ImgHeight : 0
     * ImgWidth : 0
     * SubMsgType : 0
     * NewMsgId : 8820250664544778582
     */

    private List<AddMsgListEntity> AddMsgList;

    public void setAddMsgList(List<AddMsgListEntity> AddMsgList) {
        this.AddMsgList = AddMsgList;
    }

    public List<AddMsgListEntity> getAddMsgList() {
        return AddMsgList;
    }

    public static class AddMsgListEntity {
        private String MsgId;
        private String FromUserName;
        private String ToUserName;
        private int MsgType;
        private String Content;
        private int Status;
        private int ImgStatus;
        private int CreateTime;
        private int VoiceLength;
        private int PlayLength;
        private String FileName;
        private String FileSize;
        private String MediaId;
        private String Url;
        private int AppMsgType;
        private int StatusNotifyCode;
        private String StatusNotifyUserName;

        public void setMsgId(String MsgId) {
            this.MsgId = MsgId;
        }

        public void setFromUserName(String FromUserName) {
            this.FromUserName = FromUserName;
        }

        public void setToUserName(String ToUserName) {
            this.ToUserName = ToUserName;
        }

        public void setMsgType(int MsgType) {
            this.MsgType = MsgType;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public void setImgStatus(int ImgStatus) {
            this.ImgStatus = ImgStatus;
        }

        public void setCreateTime(int CreateTime) {
            this.CreateTime = CreateTime;
        }

        public void setVoiceLength(int VoiceLength) {
            this.VoiceLength = VoiceLength;
        }

        public void setPlayLength(int PlayLength) {
            this.PlayLength = PlayLength;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public void setFileSize(String FileSize) {
            this.FileSize = FileSize;
        }

        public void setMediaId(String MediaId) {
            this.MediaId = MediaId;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public void setAppMsgType(int AppMsgType) {
            this.AppMsgType = AppMsgType;
        }

        public void setStatusNotifyCode(int StatusNotifyCode) {
            this.StatusNotifyCode = StatusNotifyCode;
        }

        public void setStatusNotifyUserName(String StatusNotifyUserName) {
            this.StatusNotifyUserName = StatusNotifyUserName;
        }

        public String getMsgId() {
            return MsgId;
        }

        public String getFromUserName() {
            return FromUserName;
        }

        public String getToUserName() {
            return ToUserName;
        }

        public int getMsgType() {
            return MsgType;
        }

        public String getContent() {
            return Content;
        }

        public int getStatus() {
            return Status;
        }

        public int getImgStatus() {
            return ImgStatus;
        }

        public int getCreateTime() {
            return CreateTime;
        }

        public int getVoiceLength() {
            return VoiceLength;
        }

        public int getPlayLength() {
            return PlayLength;
        }

        public String getFileName() {
            return FileName;
        }

        public String getFileSize() {
            return FileSize;
        }

        public String getMediaId() {
            return MediaId;
        }

        public String getUrl() {
            return Url;
        }

        public int getAppMsgType() {
            return AppMsgType;
        }

        public int getStatusNotifyCode() {
            return StatusNotifyCode;
        }

        public String getStatusNotifyUserName() {
            return StatusNotifyUserName;
        }
    }
}
