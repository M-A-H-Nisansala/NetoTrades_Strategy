import java.util.ArrayList;
import java.util.Date;


public class Agent {
	
	private double ReservePrice;
	private Date Deadline;
	private ArrayList<Offer> offerHistory;
	
	public Agent(double ReservePrice, Date Deadline){
		this.ReservePrice = ReservePrice;
		this.Deadline = Deadline;
		this.offerHistory = new ArrayList<Offer>();
	}

	public double getReservePrice() {
		return ReservePrice;
	}

	public void setReservePrice(double reservePrice) {
		ReservePrice = reservePrice;
	}

	public Date getDeadline() {
		return Deadline;
	}

	public void setDeadline(Date deadline) {
		Deadline = deadline;
	}
	
	public void addNewOffer(Offer newOffer){
		this.offerHistory.add(newOffer);
	}
	
	public ArrayList<Offer> getOfferHistory(){
		return offerHistory;
	}
	
	public Offer getLastOffer(){
		return offerHistory.get(offerHistory.size() - 1);
	}
}
