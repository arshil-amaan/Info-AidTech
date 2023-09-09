import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRecordGUI {
    private final JFrame frame;
    private final DefaultListModel<String> studentListModel;
    private final List<Student> studentList;

    public StudentRecordGUI() {
        frame = new JFrame("Student Record Management System by Arshil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);

        studentListModel = new DefaultListModel<>();
        JList<String> studentJList = new JList<>(studentListModel);
        JScrollPane scrollPane = new JScrollPane(studentJList);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        studentList = new ArrayList<>();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent(studentJList.getSelectedValue());
            }
        });

        frame.setVisible(true);
    }

    private void addStudent() {
        String name = JOptionPane.showInputDialog(frame, "Enter student name:");
        if (name != null) {
            String rollNumber = JOptionPane.showInputDialog(frame, "Student Roll Number:");
            String address = JOptionPane.showInputDialog(frame, "Student Address:");
            String phoneNumber = JOptionPane.showInputDialog(frame, "Student Phone Number:");

            Student student = new Student(rollNumber, name, address, phoneNumber);
            studentList.add(student);
            studentListModel.addElement(student.name() + " (" + student.rollNumber() + ")" + " (" + student.phoneNumber() + ")" + " (" + student.address() + ")");
            try {
                FileOutputStream fos = new FileOutputStream("student_data.txt", true);
                DataOutputStream dos = new DataOutputStream(fos);
                String t=student.name()+";"+student.rollNumber()+";"+student.address()+";"+student.phoneNumber()+";";
                System.out.print(t+"\n");
                dos.writeUTF(t+"\n");
                JOptionPane.showMessageDialog(frame, "Student Record Saved Successfully");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error while appending data: " + e.getMessage());
            }
        }
    }

    private void removeStudent(String selectedValue) {
        if (selectedValue != null) {
            for (Student student : studentList) {
                if (student.name().equals(selectedValue.split(" ")[0])) {
                    studentList.remove(student);
                    studentListModel.removeElement(selectedValue);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentRecordGUI::new);
    }
}

record Student(String rollNumber, String name, String address, String phoneNumber) implements Serializable {
}
