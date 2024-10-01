
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final NurseRepository nurseRepository;

    public EmployeeController(EmployeeService employeeService, NurseRepository nurseRepository) {
        this.employeeService = employeeService;
        this.nurseRepository = nurseRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    
    @GetMapping("/{employeeNumber}")
    public ResponseEntity<Employee> findEmployee(@PathVariable String employeeNumber) {
        Optional<Employee> employee = employeeService.findEmployee(employeeNumber);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findBySurname/{surname}")
    public List<Employee> findEmployeesBySurname(@PathVariable String surname) {
        return employeeService.findEmployeesBySurname(surname);
    }

    @GetMapping("/nurses")
    public List<Nurse> getNurses() {
        return nurseRepository.findAll();
    }

    @GetMapping("/doctors/findBySpecialization/{specialization}")
    public List<Doctor> findDoctorsBySpecialization(@PathVariable String specialization) {
        return employeeService.findDoctorsBySpecialization(specialization);
    }


    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor savedDoctor = employeeService.saveDoctor(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    @PutMapping("/nurses")
    public ResponseEntity<Nurse> updateNurse(@Valid @RequestBody Nurse nurse) {
        Nurse updatedNurse = employeeService.saveNurse(nurse);
        return ResponseEntity.ok(updatedNurse);
    }
    @PutMapping("/doctors")
    public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor updatedDoctor = employeeService.saveDoctor(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PostMapping("/nurses")
    public ResponseEntity<Nurse> createNurse(@Valid @RequestBody Nurse nurse) {
        Nurse savedNurse = employeeService.saveNurse(nurse);
        return ResponseEntity.ok(savedNurse);
    }

    @DeleteMapping("/delete/{employeeNumber}")
    public ResponseEntity<Void> deleteEmployeeByEmployeeNumber(@PathVariable String employeeNumber) {
        employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);
        return ResponseEntity.noContent().build();
    }


}
