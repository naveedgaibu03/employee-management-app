import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UI extends JFrame {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    private JList<String> employeeList;
    private DefaultListModel<String> employeeListModel;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField departmentField;

    public UI() {
        setTitle("Employee Management");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a list for displaying employees
        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);
        JScrollPane listScrollPane = new JScrollPane(employeeList);

        // Create input fields for adding employees
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        departmentField = new JTextField(20);

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String department = departmentField.getText();

                Employee employee = new Employee(name, email, department);
                employees.add(employee);

                employeeListModel.addElement(((Component) employee).getName());

                // Clear input fields
                nameField.setText("");
                emailField.setText("");
                departmentField.setText("");
            }
        });

        // Create a panel for adding employees
        JPanel addEmployeePanel = new JPanel(new GridLayout(4, 2));
        addEmployeePanel.add(new JLabel("Name:"));
        addEmployeePanel.add(nameField);
        addEmployeePanel.add(new JLabel("Email:"));
        addEmployeePanel.add(emailField);
        addEmployeePanel.add(new JLabel("Department:"));
        addEmployeePanel.add(departmentField);
        addEmployeePanel.add(new JLabel()); // Empty label for layout
        addEmployeePanel.add(addButton);

        // Create the main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(listScrollPane, BorderLayout.CENTER);
        contentPanel.add(addEmployeePanel, BorderLayout.SOUTH);

        add(contentPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UI();
            }
        });
    }
}
