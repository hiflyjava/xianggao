package cc.mrbird.web.dto.in;

import java.io.Serializable;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/24 16:01
 * @Description:
 */
public class UserPageIn implements Serializable {



      private int pageSize=10;

      private int currentPage=1;

      private String  registedAddress;

      private String  isCompany;

      private String  profession;

      private String  workType;

      private String  experienceLevel;


      private String  nickName;  //昵称


      public int getPageSize() {
            return pageSize;
      }

      public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
      }

      public int getCurrentPage() {
            return currentPage;
      }

      public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
      }

      public String getRegistedAddress() {
            return registedAddress;
      }

      public void setRegistedAddress(String registedAddress) {
            this.registedAddress = registedAddress;
      }

      public String getIsCompany() {
            return isCompany;
      }

      public void setIsCompany(String isCompany) {
            this.isCompany = isCompany;
      }

      public String getProfession() {
            return profession;
      }

      public void setProfession(String profession) {
            this.profession = profession;
      }

      public String getWorkType() {
            return workType;
      }

      public void setWorkType(String workType) {
            this.workType = workType;
      }

      public String getExperienceLevel() {
            return experienceLevel;
      }

      public void setExperienceLevel(String experienceLevel) {
            this.experienceLevel = experienceLevel;
      }


      public String getNickName() {
            return nickName;
      }

      public void setNickName(String nickName) {
            this.nickName = nickName;
      }
}
