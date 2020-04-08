package dto;

public class Contract {

    private String name;
    private int contractNumber;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public int getContractNumber()
    {
        return contractNumber;
    }
    public void setContractNumber(int contractNumber)
    {
        this.contractNumber=contractNumber;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "name='" + name + '\'' +
                ", contractNumber=" + contractNumber +
                '}';
    }
}
