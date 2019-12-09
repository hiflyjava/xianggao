package cc.mrbird.web.dto.out;
import	java.io.Serializable;

public class IndexUserOut implements Serializable {



    private int todayNum;

    private int weekNum;

    private int mouthNum;

    private  int   helfYear;

    private int oneYear;


    public int getTodayNum() {
        return todayNum;
    }

    public void setTodayNum(int todayNum) {
        this.todayNum = todayNum;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public int getMouthNum() {
        return mouthNum;
    }

    public void setMouthNum(int mouthNum) {
        this.mouthNum = mouthNum;
    }

    public int getHelfYear() {
        return helfYear;
    }

    public void setHelfYear(int helfYear) {
        this.helfYear = helfYear;
    }

    public int getOneYear() {
        return oneYear;
    }

    public void setOneYear(int oneYear) {
        this.oneYear = oneYear;
    }
}
