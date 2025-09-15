package com.utephonehub.dto.address;

import java.sql.Timestamp;

public class AddressDTOs {

    public static class AddressRequest {
        public String recipientName;
        public String phoneNumber;
        public String streetAddress;
        public String city;
        public Boolean isDefault;
    }

    public static class AddressResponse {
        public int id;
        public String recipientName;
        public String phoneNumber;
        public String streetAddress;
        public String city;
        public boolean isDefault;
        public Timestamp createdAt;
        public Timestamp updatedAt;
    }
}


