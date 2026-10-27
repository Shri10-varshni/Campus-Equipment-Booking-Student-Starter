package edu.university.equipment;
import static org.junit.jupiter.api.Assertions.*; import java.util.NoSuchElementException; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
class BookingServiceTest {
 private BookingService service; @BeforeEach void setup(){service=new BookingService();}
 @Test void successfulBookingReducesInventory(){service.create("STU1001","LAPTOP01",2);assertEquals(8,service.getEquipment("LAPTOP01").getAvailableQuantity());}
 @Test void zeroQuantityRejected(){assertThrows(IllegalArgumentException.class,()->service.create("STU1001","LAPTOP01",0));assertEquals(10,service.getEquipment("LAPTOP01").getAvailableQuantity());}
 @Test void blankBorrowerRejected(){assertThrows(IllegalArgumentException.class,()->service.create(" ","LAPTOP01",1));}
 @Test void unknownEquipmentRejected(){assertThrows(NoSuchElementException.class,()->service.create("STU1001","UNKNOWN",1));}
 @Test void ordinaryOverbookingRejected(){assertThrows(IllegalStateException.class,()->service.create("STU1001","PROJECTOR01",6));assertEquals(4,service.getEquipment("PROJECTOR01").getAvailableQuantity());}
 
@Test
void cancellationRestoresInventoryOnce() {
    Booking booking = service.create("STU1001", "TABLET01", 3);

    assertEquals("CONFIRMED", booking.getStatus());
    assertEquals(5, service.getEquipment("TABLET01").getAvailableQuantity());

    Booking cancelled = service.cancel(booking.getId());

    assertEquals("CANCELLED", cancelled.getStatus());
    assertEquals(8, service.getEquipment("TABLET01").getAvailableQuantity());

    assertThrows(
        IllegalStateException.class,
        () -> service.cancel(booking.getId())
    );

    assertEquals("CANCELLED", service.getBooking(booking.getId()).getStatus());
    assertEquals(8, service.getEquipment("TABLET01").getAvailableQuantity());
}


@Test
void bookingExactlyAvailableInventorySucceeds() {
    Booking booking = service.create("STU1001", "LAPTOP01", 10);

    assertEquals(10, booking.getQuantity());
    assertEquals("CONFIRMED", booking.getStatus());
    assertEquals(0, service.getEquipment("LAPTOP01").getAvailableQuantity());
}

@Test
void bookingOneMoreThanAvailableIsRejectedWithoutSideEffects() {
    int availableBefore = service.getEquipment("LAPTOP01").getAvailableQuantity();

    assertThrows(
        IllegalStateException.class,
        () -> service.create("STU1001", "LAPTOP01", availableBefore + 1)
    );

    assertEquals(
        availableBefore,
        service.getEquipment("LAPTOP01").getAvailableQuantity()
    );

    assertTrue(service.listBookings().isEmpty());
}

}