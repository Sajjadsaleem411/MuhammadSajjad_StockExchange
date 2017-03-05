package app.stockexchange.com.Model;

/**
 * Created by Muhammad Sajjad on 3/5/2017.
 */
public class Data {
    String name;
    int image;
    String detail;

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    String found;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    float price;


    public Data(){

    }
    public Data(String name,int image,String detail,float price){

        this.name=name;
        this.image=image;
        this.detail=detail;
        this.price=price;
    }

}
