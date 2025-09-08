package com.utephonehub.user.dto;

public class AddressDTOs {

    public static class AddressRequest {
        public String receiverName;
        public String phone;
        public String line1;
        public String line2;
        public String ward;
        public String district;
        public String city;
        public Boolean isDefault;
    }

    public static class AddressResponse {
        public long id;
        public String receiverName;
        public String phone;
        public String line1;
        public String line2;
        public String ward;
        public String district;
        public String city;
        public boolean isDefault;
        public String createdAt;
        public String updatedAt;
    }
}


