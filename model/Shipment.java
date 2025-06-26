package model;

public class Shipment {
    private String shipmentId;
    private String orderId;
    private String status;

    public Shipment(String shipmentId, String orderId) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.status = "Shipped";
    }

    public String getShipmentId() { return shipmentId; }
    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }
}
