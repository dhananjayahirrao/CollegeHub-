package Info.Student.Details.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Info.Student.Details.Entity.Student;
import Info.Student.Details.Repository.StudentRepository;

@Service
public class StudentService {
 
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }   

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            student.setEmail(updatedStudent.getEmail());
            student.setAddress(updatedStudent.getAddress());
            student.setCourse(updatedStudent.getCourse());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found!"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
