package API.POJO;

import java.util.ArrayList;
import java.util.Date;

    public class GetListOrderResponse {
        private int id;
        private Object courierId;
        private String firstName;
        private String lastName;
        private String address;
        private String metroStation;
        private String phone;
        private int rentTime;
        private Date deliveryDate;
        private int track;
        private ArrayList<String> color;
        private String comment;
        private Date createdAt;
        private Date updatedAt;
        private int status;

        public int getId() {
            return id;
        }

        public Object getCourierId() {
            return courierId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getAddress() {
            return address;
        }

        public String getMetroStation() {
            return metroStation;
        }

        public String getPhone() {
            return phone;
        }

        public int getRentTime() {
            return rentTime;
        }

        public Date getDeliveryDate() {
            return deliveryDate;
        }

        public int getTrack() {
            return track;
        }

        public ArrayList<String> getColor() {
            return color;
        }

        public String getComment() {
            return comment;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public int getStatus() {
            return status;
        }

        public GetListOrderResponse(int id, Object courierId, String firstName, String lastName, String address, String metroStation, String phone, int rentTime, Date deliveryDate, int track, ArrayList<String> color, String comment, Date createdAt, Date updatedAt, int status) {
            this.id = id;
            this.courierId = courierId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.metroStation = metroStation;
            this.phone = phone;
            this.rentTime = rentTime;
            this.deliveryDate = deliveryDate;
            this.track = track;
            this.color = color;
            this.comment = comment;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.status = status;
        }

        public GetListOrderResponse() {
        }
    }

