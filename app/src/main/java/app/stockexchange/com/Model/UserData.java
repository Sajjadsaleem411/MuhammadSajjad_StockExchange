package app.stockexchange.com.Model;

/**
 * Created by Muhammad Sajjad on 3/5/2017.
 */
public class UserData {
    public UserData(){

        setSell_price(0.0f);
        setProfit(0.0f);
        setLost(0.0f);

    }

    public float getSell_price() {
        return sell_price;
    }

    public void setSell_price(float sell_price) {
        this.sell_price = sell_price;
    }

    public float getProfit()
    {
            return profit;

    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    float sell_price=0.0f;
    float profit=0.0f;

    public float getLost() {
        return lost;
    }

    public void setLost(float lost) {
        this.lost = lost;
    }

    float lost;

}
