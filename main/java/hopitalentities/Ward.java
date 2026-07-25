package hopitalentities;

public class Ward {
    private String WardName;
    private int wardId;
    private int wardCapacity;

    public Ward(String wardName, int wardId, int wardCapacity) {
        WardName = wardName;
        this.wardId = wardId;
        this.wardCapacity = wardCapacity;
    }

    public String getWardName() {
        return WardName;
    }

    public void setWardName(String wardName) {
        WardName = wardName;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getWardCapacity() {
        return wardCapacity;
    }

    public void setWardCapacity(int wardCapacity) {
        this.wardCapacity = wardCapacity;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "WardName='" + WardName + '\'' +
                ", wardId=" + wardId +
                ", wardCapacity=" + wardCapacity +
                '}';
    }
}
