package ua.nure.todo.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode(callSuper = false)
public class Task extends BaseEntity {
    private Long id;
    private String title;
    private Integer completed; // 1 = true, 0 = false
    private Date date;
    private Priority priority;
    private Category category;

    // указываем, что поле заполняется в БД
    // нужно, когда добавляем новый объект и он возвращается уже с новым id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "completed")
    public Integer getCompleted() {
        return completed;
    } // 1 = true, 0 = false


    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    // ссылка на объект Priority
    // одна задача имеет ссылку на один объект
    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
    public Priority getPriority() {
        return priority;
    }

    // ссылка на объект Category
    // одна задача имеет ссылку на один объект
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
    public Category getCategory() {
        return category;
    }
}
