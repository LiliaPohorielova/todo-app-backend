package ua.nure.todo.web.controller.priority;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.todo.exception.EntityNotFoundException;
import ua.nure.todo.facade.priority.PriorityFacade;
import ua.nure.todo.search.PrioritySearchValues;
import ua.nure.todo.util.ConsoleLoggerSQL;
import ua.nure.todo.web.dto.request.priority.PriorityRequestDto;
import ua.nure.todo.web.dto.response.priority.PriorityResponseDto;

import java.util.List;

@RestController
@RequestMapping("/priority")
@CrossOrigin(origins = "http://localhost:4200")
public class PriorityController {

    // Контроллеры напрямую с репозиториями не работают
    // Внедряем ссылку на объект (Dep Inj PriorityRepository)
    private final PriorityFacade priorityFacade;

    public PriorityController(PriorityFacade priorityRepository) {
        this.priorityFacade = priorityRepository;
    }

    @GetMapping("/all")
    public List<PriorityResponseDto> findAll() {
        ConsoleLoggerSQL.logMethod("PriorityRepository: findAll()");
        return priorityFacade.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<PriorityResponseDto> addPriority(@RequestBody PriorityRequestDto priority) {
        ConsoleLoggerSQL.logMethod("PriorityRepository: addPriority()");

        // Название нового объекта не должно быть пустым
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Priority name must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        // Цвет нового объекта не должно быть пустым
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Priority color must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityFacade.create(priority));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PriorityResponseDto> editPriority(@PathVariable Long id, @RequestBody PriorityRequestDto priorityDTO) {
        ConsoleLoggerSQL.logMethod("PriorityRepository: editPriority()");
        PriorityResponseDto priority;
        // Id редактированного объекта НЕ должен быть пустым
        if (id == null || id == 0) {
            return new ResponseEntity("ID of edit priority must not be null", HttpStatus.NOT_ACCEPTABLE);
        }

        // Название редактированного объекта не должно быть пустым
        if (priorityDTO.getTitle() == null || priorityDTO.getTitle().trim().length() == 0) {
            return new ResponseEntity("Priority name must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        // Цвет редактированного объекта не должно быть пустым
        if (priorityDTO.getColor() == null || priorityDTO.getColor().trim().length() == 0) {
            return new ResponseEntity("Priority color must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            priority = priorityFacade.update(priorityDTO, id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity("Priority with id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        // Метод save() работает как на создание так и на обновление
        return ResponseEntity.ok(priority);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PriorityResponseDto> findById(@PathVariable Long id) {
        ConsoleLoggerSQL.logMethod("PriorityRepository: findById()");
        PriorityResponseDto priority;
        try {
            priority = priorityFacade.findById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity("Priority with id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        ConsoleLoggerSQL.logMethod("PriorityRepository: deleteById()");
        try {
            priorityFacade.delete(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity("Priority with id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    // поиск по любым параметрам
    // отправляем JSON и по нем будем искать
    // чтобы не перечислять все параметры для поиска через запятую
    @PostMapping("/search")
    public ResponseEntity<List<PriorityResponseDto>> searchPriorities(@RequestBody PrioritySearchValues prioritySearchValues) {
        ConsoleLoggerSQL.logMethod("PriorityRepository: searchPriorities()");
        // если не найдется ничего - будут показаны все категории
        return ResponseEntity.ok(priorityFacade.findByTitle(prioritySearchValues.getTitle()));
    }
}
