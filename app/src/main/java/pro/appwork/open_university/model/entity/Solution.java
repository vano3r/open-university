package pro.appwork.open_university.model.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_solution")
public class Solution {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String filePath;

    @Column
    private Integer version;

    @ManyToOne
    private Task task;
}
