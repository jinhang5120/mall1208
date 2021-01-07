package com.jh.common.constant;

public class ProductConstant {
    public enum attr{
        ATTR_TYPE_SALE(0,"SALE"),
        ATTR_TYPE_BASE(1,"base");
        private int attrType;
        private String attrTypeName;

        attr(int attrType, String attrTypeName) {
            this.attrType = attrType;
            this.attrTypeName = attrTypeName;
        }

        public int getAttrType() {
            return attrType;
        }

        public void setAttrType(int attrType) {
            this.attrType = attrType;
        }
    }
    public enum spuStatus{
        SPU_NEW(0,"新建"),
        SPU_UP(1,"上架"),
        SPU_DOWN(2,"下架");
        private int status;
        private String statusName;

        spuStatus(int status, String statusName) {
            this.status = status;
            this.statusName = statusName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
