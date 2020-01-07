package CarPack;

import javax.swing.*;

public class AddCar extends JFrame{

    private JPanel Panel;
    private JTextField nrrej;
    private JTextField obw;
    private JTextField max;
    private JTextField model;
    private JButton commit;
    private Car car;

    Position poz = new Position(0,0);
    String ENGINENAME = "diesel";
    int WEIGHT = 100;
    int PRICE = 10000;
    int RPM = 5000;

    Engine engine = new Engine(ENGINENAME, WEIGHT, PRICE, RPM);
    Clutch clutch = new Clutch("HQAA 21022", 5, 500);
    GearBox gearbox = new GearBox("HRQ 210", 20, 4000, 6, clutch);

    public AddCar() {
        setContentPane(Panel);
        pack();
        commit.addActionListener(actionEvent -> {
            try{
            boolean check = true;
            for(Car sam: CarUtilities.list){
                if(sam.getRegistrationNumber() == (int)Double.parseDouble(nrrej.getText())){
                    check = false;
                }
            }

            if(check) {
                car = CarUtilities.carCreator((int) Double.parseDouble(nrrej.getText()), model.getText(), (int) Double.parseDouble(max.getText()), Double.parseDouble(obw.getText()));
                CarUtilities.list.add(car);
            }
            else{
                System.out.println("INVALID PLATE NUMBER");
            }
            } catch (NumberFormatException e){
                System.out.println("BAD PARAMETERS");
            }
        });
    }
}
