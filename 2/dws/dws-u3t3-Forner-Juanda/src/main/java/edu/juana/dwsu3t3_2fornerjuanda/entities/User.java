package edu.juana.dwsu3t3_2fornerjuanda.entities;

import edu.juana.dwsu3t3_2fornerjuanda.models.LoginForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class User {
    private String nombre;
    private String email;
    private String password;
    private String fechaNacimiento;

    public boolean equals(LoginForm loginUser) {
        return loginUser.getUsername().equals(this.nombre) && loginUser.getPassword().equals(this.password);
    }
}
