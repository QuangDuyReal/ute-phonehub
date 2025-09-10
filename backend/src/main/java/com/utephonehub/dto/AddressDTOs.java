package com.utephonehub.dto;

public class AddressDTOs {

    public static class AddressRequest {
        public String recipientName;
        public String phoneNumber;
        public String streetAddress;
        public String city;
        public Boolean isDefault;
    }

    public static class AddressResponse {
        public long id;
        public String recipientName;
        public String phoneNumber;
        public String streetAddress;
        public String city;
        public boolean isDefault;
        public String createdAt;
        public String updatedAt;
    }
}


