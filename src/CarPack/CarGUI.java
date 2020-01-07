package CarPack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class CarGUI {
    private JButton increaseButton;
    private JButton reduceButton;
    private JTextField carName;
    private JTextField registratonNumber;
    private JTextField currentSpeed;
    private JTextField currentX;
    private JTextField currentY;
    private JTextField x;
    private JTextField y;
    private JButton goToButton;
    private JPanel Skrzynia;
    private JTextField currentGear;
    private JTextField currentRatio;
    private JTextField engineName;
    private JTextField currentRPM;
    private JTextField gearboxName;
    private JButton goFasterButton;
    private JButton goSlowerButton;
    private JLabel carOnMap;
    private JPanel Panel;
    private JPanel Mapa;
    private JComboBox<Car> Combobox;
    private JButton AddNew;
    private JButton delCur;
    private AddCar newCar;

    public CarGUI(Car s) {
        Car car = s;

        for (Car sam : CarUtilities.list) {
            Combobox.addItem(sam);
        }
        car.start();
        Timer timer = new Timer(100, e -> odswiez(car));
        timer.start();

        goToButton.addActionListener(actionEvent -> {
            car.setRunning(false);
            try {
                Thread.sleep(520);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                car.setDest(Double.parseDouble(x.getText()), Double.parseDouble(y.getText()));
            } catch (NumberFormatException e) {
                System.out.println("Wrong X Y format!");
            }
            car.setRunning(true);
        });

        increaseButton.addActionListener(actionEvent -> car.gearUp());
        reduceButton.addActionListener(actionEvent -> car.gearDown());
        goFasterButton.addActionListener(actionEvent -> car.RPMup());
        goSlowerButton.addActionListener(actionEvent -> car.RPMdown());

        Mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                car.setRunning(false);
                try {
                    Thread.sleep(520);
                } catch (InterruptedException x) {
                    x.printStackTrace();
                }
                car.setDest(e.getX(), e.getY());
                x.setText(String.valueOf(e.getX()));
                y.setText(String.valueOf(e.getY()));
                car.setRunning(true);
            }
        });

        Combobox.addActionListener(actionEvent -> {
            Car s1 = (Car) Combobox.getSelectedItem();
            if (!CarUtilities.ehh) {
                try {
                    if (!s1.isActive()) {
                        JFrame frame = new JFrame(Integer.toString(s1.getRegistrationNumber()));
                        frame.setContentPane(new CarGUI(s1).Panel);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                    }
                } catch (IllegalThreadStateException e) {
                    System.out.println("Already open!");
                } catch (NullPointerException e) {
                    System.out.println("NullPointerException!");
                }
            } else {
                CarUtilities.ehh = false;
            }
        });
        AddNew.addActionListener(actionEvent -> {
            newCar = new AddCar();
            newCar.setVisible(true);
        });
        delCur.addActionListener(actionEvent -> {
            try {
                CarUtilities.list.remove(car);
                CarUtilities.removed = car;
                CarUtilities.ehh = true;
            } catch (NullPointerException e) {
            }
        });
    }

    public void odswiez(Car car) {
        currentSpeed.setText(Double.toString(car.getV()));
        currentX.setText(Double.toString(car.getCurrentPosition().getX()));
        currentY.setText(Double.toString(car.getCurrentPosition().getY()));
        carOnMap.setText(car.getModel());
        try {
            String DEFAULT_IMAGE_PATH = "src/car.gif";
            carOnMap.setIcon(new ImageIcon(ImageIO.read(new File(DEFAULT_IMAGE_PATH))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        carOnMap.setLocation((int) Double.parseDouble(currentX.getText()), (int) Double.parseDouble(currentY.getText()));
        carName.setText(car.getModel());
        engineName.setText(car.getSilnikNazw());
        gearboxName.setText(car.getSkrzNazw());
        registratonNumber.setText(Integer.toString(car.getRegistrationNumber()));
        currentGear.setText(Double.toString(car.getCurrentGear()));
        currentRatio.setText(Double.toString(car.getCurrentRatio()));
        currentRPM.setText(Double.toString(car.getCurrentRPM()));

        if (CarUtilities.list.size() > Combobox.getItemCount()) {
            Combobox.addItem(CarUtilities.list.get(CarUtilities.list.size() - 1));
        } else if(CarUtilities.list.size() < Combobox.getItemCount()) {
            Combobox.removeItem(CarUtilities.removed);
        }
    }


    public static void main(String[] args) {

        Car car = CarUtilities.carCreator();
        CarUtilities.list.add(car);
        JFrame frame = new JFrame("MAIN WINDOW, DO NOT CLOSE");
        frame.setContentPane(new CarGUI(car).Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
