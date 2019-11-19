package cc.mrbird.common.aws;

/**
 * @Auther: admin
 * @Date: 2019/8/29 10:47
 * @Description:
 */
public class IotClientSetting {




        private String clientName;
        private String accessKeyID;
        private String accessKeySecret;
        private String regionId;
        private String productKey;
        private String domain;
        private String mqttDomain;

        public IotClientSetting() {
        }

        public String getClientName() {
            return this.clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getAccessKeyID() {
            return this.accessKeyID;
        }

        public void setAccessKeyID(String accessKeyID) {
            this.accessKeyID = accessKeyID;
        }

        public String getAccessKeySecret() {
            return this.accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getRegionId() {
            return this.regionId;
        }

        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

        public String getProductKey() {
            return this.productKey;
        }

        public void setProductKey(String productKey) {
            this.productKey = productKey;
        }

        public String getDomain() {
            return this.domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getMqttDomain() {
            return this.mqttDomain;
        }

        public void setMqttDomain(String mqttDomain) {
            this.mqttDomain = mqttDomain;
        }

        public String toString() {
            return "RemoteServiceSettingBasic [clientName=" + this.clientName + ", accessKeyID=" + this.accessKeyID + ", accessKeySecret=" + this.accessKeySecret + ", regionId=" + this.regionId + ", productKey=" + this.productKey + ", domain=" + this.domain + ", mqttDomain=" + this.mqttDomain + "]";
        }


}
