import org.junit.After;
import org.junit.Test;

public class AuctionSniperEndToEndTest {

    private final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
    private final ApplicationRunner application = new ApplicationRunner();

    @Test
    public void sniperJoinsAuctionUntilAuctionCloses() {
        auction.startSellingItem();

        //Step1: 
        application.startBiddingIn(auction);

        // Step2:
        application.hasReceivedJoinRequestFromSniper();

        // Step3:
        auction.announceClosed();

        // Step4:
        application.showsSniperHasLostAuction();

    }

    @After
    public void stopAuction() {
        auction.stop();
    }

    @After
    public void stopApplication() {
        application.stop();
    }
}
