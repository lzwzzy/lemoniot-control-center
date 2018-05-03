package com.lzw.lemoniot.e;

/**
 * 错误枚举
 * @author lzw
 * @date 2018/4/6 22:25
 **/
public enum ErrorEnum {
    ;

    public interface CodeMessageEnum {
        String getMessage();

        String getCode();
    }


    /**
     * systemError
     */
    public enum System implements CodeMessageEnum {
        /**
         *  系统级错误
         */
        SYSTEM_ERROR("1005", "Internal Server Error"),
        ;

        private String code;
        private String message;

        System(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public String getCode() {
            return this.code;
        }
    }
}
