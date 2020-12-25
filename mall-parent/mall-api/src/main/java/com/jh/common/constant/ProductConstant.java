package com.jh.common.constant;

public class ProductConstant {
    public enum attr{
        ATTR_TYPE_SALE(0,"SALE"),ATTR_TYPE_BASE(1,"base");
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
}
