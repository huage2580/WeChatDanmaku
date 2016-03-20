package pack;



/**
 * Created by hua on 2016/3/18.
 */
public class BaseResponeBean {
    /**
     * Ret : 0
     * ErrMsg :
     */

    private BaseResponseEntity BaseResponse;
    

    private int Count;
    /**
     * Count : 4
     * List : [{"Key":1,"Val":641466310},{"Key":2,"Val":641466407},{"Key":3,"Val":641466406},{"Key":1000,"Val":1458264441}]
     */

    private SyncKeyEntity SyncKey;
    

    private UserEntity User;
    private String ChatSet;
    private String SKey;
    private int ClientVersion;
    private int SystemTime;
    private int GrayScale;
    private int InviteStartCount;
    private int MPSubscribeMsgCount;
    private int ClickReportInterval;

    public void setBaseResponse(BaseResponseEntity BaseResponse) {
        this.BaseResponse = BaseResponse;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public void setSyncKey(SyncKeyEntity SyncKey) {
        this.SyncKey = SyncKey;
    }

    public void setUser(UserEntity User) {
        this.User = User;
    }

    public void setChatSet(String ChatSet) {
        this.ChatSet = ChatSet;
    }

    public void setSKey(String SKey) {
        this.SKey = SKey;
    }

    public void setClientVersion(int ClientVersion) {
        this.ClientVersion = ClientVersion;
    }

    public void setSystemTime(int SystemTime) {
        this.SystemTime = SystemTime;
    }

    public void setGrayScale(int GrayScale) {
        this.GrayScale = GrayScale;
    }

    public void setInviteStartCount(int InviteStartCount) {
        this.InviteStartCount = InviteStartCount;
    }

    public void setMPSubscribeMsgCount(int MPSubscribeMsgCount) {
        this.MPSubscribeMsgCount = MPSubscribeMsgCount;
    }

    public void setClickReportInterval(int ClickReportInterval) {
        this.ClickReportInterval = ClickReportInterval;
    }

    public BaseResponseEntity getBaseResponse() {
        return BaseResponse;
    }

    public int getCount() {
        return Count;
    }

    public SyncKeyEntity getSyncKey() {
        return SyncKey;
    }

    public UserEntity getUser() {
        return User;
    }

    public String getChatSet() {
        return ChatSet;
    }

    public String getSKey() {
        return SKey;
    }

    public int getClientVersion() {
        return ClientVersion;
    }

    public int getSystemTime() {
        return SystemTime;
    }

    public int getGrayScale() {
        return GrayScale;
    }

    public int getInviteStartCount() {
        return InviteStartCount;
    }

    public int getMPSubscribeMsgCount() {
        return MPSubscribeMsgCount;
    }

    public int getClickReportInterval() {
        return ClickReportInterval;
    }

    public static class BaseResponseEntity {
        private int Ret;
        private String ErrMsg;

        public void setRet(int Ret) {
            this.Ret = Ret;
        }

        public void setErrMsg(String ErrMsg) {
            this.ErrMsg = ErrMsg;
        }

        public int getRet() {
            return Ret;
        }

        public String getErrMsg() {
            return ErrMsg;
        }
    }

    public static class SyncKeyEntity {
        private int Count;
        /**
         * Key : 1
         * Val : 641466310
         */

        private java.util.List<ListEntity> List;

        public void setCount(int Count) {
            this.Count = Count;
        }

        public void setList(java.util.List<ListEntity> List) {
            this.List = List;
        }

        public int getCount() {
            return Count;
        }

        public java.util.List<ListEntity> getList() {
            return List;
        }

        public static class ListEntity {
            private int Key;
            private int Val;

            public void setKey(int Key) {
                this.Key = Key;
            }

            public void setVal(int Val) {
                this.Val = Val;
            }

            public int getKey() {
                return Key;
            }

            public int getVal() {
                return Val;
            }
        }
    }

    public static class UserEntity {
        private int Uin;
        private String UserName;
        private String NickName;
        private String HeadImgUrl;
        private String RemarkName;
        private String PYInitial;
        private String PYQuanPin;
        private String RemarkPYInitial;
        private String RemarkPYQuanPin;
        private int HideInputBarFlag;
        private int StarFriend;
        private int Sex;
        private String Signature;
        private int AppAccountFlag;
        private int VerifyFlag;
        private int ContactFlag;
        private int WebWxPluginSwitch;
        private int HeadImgFlag;
        private int SnsFlag;

        public void setUin(int Uin) {
            this.Uin = Uin;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public void setHeadImgUrl(String HeadImgUrl) {
            this.HeadImgUrl = HeadImgUrl;
        }

        public void setRemarkName(String RemarkName) {
            this.RemarkName = RemarkName;
        }

        public void setPYInitial(String PYInitial) {
            this.PYInitial = PYInitial;
        }

        public void setPYQuanPin(String PYQuanPin) {
            this.PYQuanPin = PYQuanPin;
        }

        public void setRemarkPYInitial(String RemarkPYInitial) {
            this.RemarkPYInitial = RemarkPYInitial;
        }

        public void setRemarkPYQuanPin(String RemarkPYQuanPin) {
            this.RemarkPYQuanPin = RemarkPYQuanPin;
        }

        public void setHideInputBarFlag(int HideInputBarFlag) {
            this.HideInputBarFlag = HideInputBarFlag;
        }

        public void setStarFriend(int StarFriend) {
            this.StarFriend = StarFriend;
        }

        public void setSex(int Sex) {
            this.Sex = Sex;
        }

        public void setSignature(String Signature) {
            this.Signature = Signature;
        }

        public void setAppAccountFlag(int AppAccountFlag) {
            this.AppAccountFlag = AppAccountFlag;
        }

        public void setVerifyFlag(int VerifyFlag) {
            this.VerifyFlag = VerifyFlag;
        }

        public void setContactFlag(int ContactFlag) {
            this.ContactFlag = ContactFlag;
        }

        public void setWebWxPluginSwitch(int WebWxPluginSwitch) {
            this.WebWxPluginSwitch = WebWxPluginSwitch;
        }

        public void setHeadImgFlag(int HeadImgFlag) {
            this.HeadImgFlag = HeadImgFlag;
        }

        public void setSnsFlag(int SnsFlag) {
            this.SnsFlag = SnsFlag;
        }

        public int getUin() {
            return Uin;
        }

        public String getUserName() {
            return UserName;
        }

        public String getNickName() {
            return NickName;
        }

        public String getHeadImgUrl() {
            return HeadImgUrl;
        }

        public String getRemarkName() {
            return RemarkName;
        }

        public String getPYInitial() {
            return PYInitial;
        }

        public String getPYQuanPin() {
            return PYQuanPin;
        }

        public String getRemarkPYInitial() {
            return RemarkPYInitial;
        }

        public String getRemarkPYQuanPin() {
            return RemarkPYQuanPin;
        }

        public int getHideInputBarFlag() {
            return HideInputBarFlag;
        }

        public int getStarFriend() {
            return StarFriend;
        }

        public int getSex() {
            return Sex;
        }

        public String getSignature() {
            return Signature;
        }

        public int getAppAccountFlag() {
            return AppAccountFlag;
        }

        public int getVerifyFlag() {
            return VerifyFlag;
        }

        public int getContactFlag() {
            return ContactFlag;
        }

        public int getWebWxPluginSwitch() {
            return WebWxPluginSwitch;
        }

        public int getHeadImgFlag() {
            return HeadImgFlag;
        }

        public int getSnsFlag() {
            return SnsFlag;
        }
    }
}
