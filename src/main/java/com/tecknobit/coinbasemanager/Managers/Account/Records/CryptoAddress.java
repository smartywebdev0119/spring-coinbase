package com.tecknobit.coinbasemanager.Managers.Account.Records;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CryptoAddress {

    private final String id;
    private final String address;
    private final ArrayList<AddressInfo> addressInfo;
    private final String name;
    private final String createdAt;
    private final String updatedAt;
    private final String network;
    private final String uriScheme;
    private final String resource;
    private final String resourcePath;
    private final String depositUri;
    private final boolean exchangeDepositAddress;
    private final ArrayList<Warning> warnings;

    public CryptoAddress(String id, String address, JSONObject jsonAddressInfo, String name, String createdAt, String updatedAt, String network,
                         String uriScheme, String resource, String resourcePath, String depositUri,
                         boolean exchangeDepositAddress, JSONArray jsonWarnings) {
        this.id = id;
        this.address = address;
        addressInfo = assembleAddressInfo(jsonAddressInfo);
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.network = network;
        this.uriScheme = uriScheme;
        this.resource = resource;
        this.resourcePath = resourcePath;
        this.depositUri = depositUri;
        this.exchangeDepositAddress = exchangeDepositAddress;
        this.warnings = assembleWarningList(jsonWarnings);
    }

    private ArrayList<AddressInfo> assembleAddressInfo(JSONObject jsonAddressInfo) {
        ArrayList<AddressInfo> addressInfo = new ArrayList<>();
        for (String addressInfoLine : jsonAddressInfo.toString().split(",")) {
            String[] addressInfoDetails = addressInfoLine.split(":");
            addressInfo.add(new AddressInfo(addressInfoDetails[0], addressInfoDetails[1]));
        }
        return addressInfo;
    }

    private ArrayList<Warning> assembleWarningList(JSONArray jsonWarnings){
        ArrayList<Warning> warnings = new ArrayList<>();
        for (int j=0; j < jsonWarnings.length(); j++){
            JSONObject warning = jsonWarnings.getJSONObject(j);
            warnings.add(new Warning(warning.getString("title"),
                    warning.getString("details"),
                    warning.getString("image_url")
            ));
        }
        return warnings;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<AddressInfo> getAddressInfo() {
        return addressInfo;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getNetwork() {
        return network;
    }

    public String getUriScheme() {
        return uriScheme;
    }

    public String getResource() {
        return resource;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getDepositUri() {
        return depositUri;
    }

    public boolean isExchangeDepositAddress() {
        return exchangeDepositAddress;
    }

    public ArrayList<Warning> getWarnings() {
        return warnings;
    }

    public static class AddressInfo{

        private final String titleInfo;
        private final String valueInfo;

        public AddressInfo(String titleInfo, String valueInfo) {
            this.titleInfo = titleInfo;
            this.valueInfo = valueInfo;
        }

        public String getTitleInfo() {
            return titleInfo;
        }

        public String getValueInfo() {
            return valueInfo;
        }

    }

    public static class Warning{

        private final String title;
        private final String details;
        private final String imageUrl;

        public Warning(String title, String details, String imageUrl) {
            this.title = title;
            this.details = details;
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getDetails() {
            return details;
        }

        public String getImageUrl() {
            return imageUrl;
        }

    }

}