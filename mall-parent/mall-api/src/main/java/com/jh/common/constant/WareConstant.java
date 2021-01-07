package com.jh.common.constant;

public class WareConstant {
    public enum PurchaseStatus{
        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        RECEIVED(2,"已领取"),
        FINISHED(3,"已完成"),
        HAS_ERROR(4,"有异常");
        private int statusCode;
        private String statusName;

        PurchaseStatus(int statusCode, String statusName) {
            this.statusCode = statusCode;
            this.statusName = statusName;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
    public enum PurchaseDetailStatus{
        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        PURCHASING(2,"正在采购"),
        FINISHED(3,"已完成"),
        PURCHASE_ERROR(4,"采购失败");
        private int statusCode;
        private String statusName;

        PurchaseDetailStatus(int statusCode, String statusName) {
            this.statusCode = statusCode;
            this.statusName = statusName;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
}
