package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SMEList {
    @SerializedName("data")
    private List<SME> sme;

    @SerializedName("data")
    public List<SME> getSme() {
        return sme;
    }

    public class SME implements Serializable
    {
        private int id;
        private String firstName;
        private String lastName;
        private String bnFirstName;
        private String bnLastName;
        private String phone;
        private String email;
        private String organization;
        private String businessType;
        private Address presentAddress;
        private District district;
        private String agentFirstName;
        private String agentLastName;

        public int getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getBnFirstName() {
            return bnFirstName;
        }

        public String getBnLastName() {
            return bnLastName;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getOrganization() {
            return organization;
        }

        public String getBusinessType() {
            return businessType;
        }

        public Address getPresentAddress() {
            return presentAddress;
        }

        public District getDistrict() {
            return district;
        }

        public String getAgentFirstName() {
            return agentFirstName;
        }

        public String getAgentLastName() {
            return agentLastName;
        }
    }
}
