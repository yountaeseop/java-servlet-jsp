package com.nhnacademy.mvc_project.repository;

import com.nhnacademy.mvc_project.Student;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MapStudentRepository implements StudentRepository {
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        studentsMap.replace(student.getId(), student);
    }

    @Override
    public void deleteById(String id) {
        studentsMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        return studentsMap.get(id);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = studentsMap.values().stream()
                .collect(Collectors.toList());
        return studentList;
    }

    @Override
    public boolean existById(String id) {
        return studentsMap.containsKey(id);
    }
}
