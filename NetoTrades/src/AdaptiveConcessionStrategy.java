import java.util.ArrayList;
import java.util.Date;

public class AdaptiveConcessionStrategy {
	
	private Calculator calculator;
	
	public AdaptiveConcessionStrategy(Calculator calculator){
		this.calculator = calculator;
	}
	
	public void FindConcessionPoint(int numberOfRows, int numberOfColumns, DetectionRegion detReg, Offer newOffer, Agent thisAgent, long stepSize, int numberOfRounds, ArrayList<Offer> offerHistory){
		
		double concessionPrice = 0.0;
		Date concessionTime = null;
		ConcessionPoint newConcessionPoint;
		
		double RegressionValueAtTb;
		double RegressionValueAtT0;
		
		for(int i = 0; i < numberOfRows; i++){
			for(int j = 0; i < numberOfColumns; j++){
				
				RegressionValueAtTb = calculator.GenerateFittedOffer(detReg, i, j, offerHistory, newOffer, numberOfRounds, thisAgent.getDeadline());
				RegressionValueAtT0 = calculator.GenerateFittedOffer(detReg, i, j, offerHistory, newOffer, numberOfRounds, );
				
				if((detReg.getCells()[i][j].getCellDeadline().getTime() < thisAgent.getDeadline().getTime()) && (detReg.getCells()[i][j].getCellReservePrice() > newOffer.getOfferPrice())){
					
					concessionPrice = detReg.getCells()[i][j].getCellReservePrice();
					concessionTime = detReg.getCells()[i][j].getCellDeadline();
					
				}
				
				else if((detReg.getCells()[i][j].getCellDeadline().getTime() >= thisAgent.getDeadline().getTime()) && (detReg.getCells()[i][j].getCellReservePrice() >= newOffer.getOfferPrice())){
					
					if(newOffer.getOfferPrice() <= thisAgent.getReservePrice()){
						concessionPrice = thisAgent.getReservePrice();
						concessionTime = new Date(thisAgent.getDeadline().getTime() - stepSize);
					}
					
					else{
						concessionPrice = thisAgent.getReservePrice()*(0.9 + (0.01 * newOffer.getRoundNumber()));
						concessionTime = new Date(newOffer.getOfferTime().getTime() + stepSize);
					}
					
				}
				
				else if((detReg.getCells()[i][j].getCellDeadline().getTime() < thisAgent.getDeadline().getTime()) && (detReg.getCells()[i][j].getCellReservePrice() < newOffer.getOfferPrice())){
					
					if(newOffer.getOfferPrice() > thisAgent.getLastOffer().getOfferPrice()){
						concessionPrice = 
					}
					
					else{
						concessionPrice = thisAgent.getLastOffer().getOfferPrice()*(1 + (0.01)*newOffer.getRoundNumber());
						concessionTime = new Date(thisAgent.getDeadline().getTime() - stepSize);
					}
					
				}
				
				else if((detReg.getCells()[i][j].getCellDeadline().getTime() >= thisAgent.getDeadline().getTime()) && (detReg.getCells()[i][j].getCellReservePrice() <= newOffer.getOfferPrice())){
					
					if(){
						
					}
					
					else if(){
						
					}
					
					else if(){
						
					}
					
					else{
						
					}
					
				}
				
				newConcessionPoint = new ConcessionPoint(concessionPrice, concessionTime);
				detReg.getCells()[i][j].setConcessionPoint(newConcessionPoint);
				
			}
		}
	}
	double buyerInitPrice = 0;
	Date buyerInitTime = null;
	double buyerCurrentOffer = 0; // b0
	double buyerNewOffer;
	double buyerReserveOffer = 0;
	Date buyerDeadline = null;
	double concessionPrice = 0;
	Date concessionTime = null;
	Date currentTime= new Date();
	double beta = 0;
	double betaHat;
	double betaHatSum = 0;
	double betaBar;
	double sumDown = 0;
	// Optimal concession strategy
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < columns; j++) {

			// Scenario 1

			if ((detReg[i][j].cellDeadlineDate.getTime() < buyerDeadline
					.getTime())
					&& (detReg[i][j].cellReservePrice > buyerCurrentOffer)) {
				
			concessionPrice= detReg[i][j].cellReservePrice;
			concessionTime= detReg[i][j].cellDeadlineDate;

			}
			// Scenario 2

			else if ((detReg[i][j].cellDeadlineDate.getTime() >= buyerDeadline
					.getTime())
					&& (detReg[i][j].cellReservePrice >= buyerCurrentOffer)) {
				//case 1: line l1
				//case 2:line l2
			}
			// Scenario 3

			else if ((detReg[i][j].cellDeadlineDate.getTime() < buyerDeadline
					.getTime())
					&& (detReg[i][j].cellReservePrice < buyerCurrentOffer)) {

			}
			// Scenario 4
			else if ((detReg[i][j].cellDeadlineDate.getTime() >= buyerDeadline
					.getTime())
					&& (detReg[i][j].cellReservePrice <= buyerCurrentOffer)) {

			} else {

			}
			// Combining mechanism
			//t>t0
			buyerNewOffer= buyerInitPrice + (buyerReserveOffer-buyerInitPrice)*Math.pow(((currentTime.getTime()-buyerInitTime.getTime())/(buyerDeadline.getTime()-buyerInitTime.getTime())),beta);
			
			float base= (concessionTime.getTime()-buyerInitTime.getTime())/(buyerDeadline.getTime()-buyerInitTime.getTime());
			double num= (buyerInitPrice-concessionPrice)/(buyerInitPrice-buyerReserveOffer); // t0<tp<Tb
			betaHat= logOfBase(base, num);
			
			betaHatSum +=betaHatSum;
			sumDown += (detReg[i][j].probability)/(1+betaHat); 
		}		
				
	}
	betaBar= (1/sumDown)-1;
	beta=betaBar;

}
