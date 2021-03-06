package bookings;

/**
 * Created by xemorth on 4/04/2017.
 */
public abstract class Bookings {

    private String dayBooked;
    private String customer;
    private String timeBooked;
    private String serviceBooked;
    private String customerID;
    private String business;

    public Bookings(String business,String dayBooked, String customer, String timeBooked, String serviceBooked, String customerID) {
        this.dayBooked = dayBooked;
        this.customer = customer;
        this.timeBooked = timeBooked;
        this.serviceBooked = serviceBooked;
        this.customerID = customerID;
        this.business = business;
    }


    public String toString() {

        return dayBooked + ":" + customer + ":" + timeBooked + ":" + serviceBooked;
    }

    public String getDayBooked() {

        return dayBooked;
    }

    public String getCustomer() {

        return customer;
    }

    public String getTimeBooked() {

        return timeBooked;
    }

    public String getServiceBooked() {

        return serviceBooked;
    }

    public String getCustomerID() {

        return customerID;
    }



}
