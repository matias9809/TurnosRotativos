import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators,FormControl } from '@angular/forms';
import { EmpleadosService } from '../../empleados.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Empleado } from '../../entidad/empleado';
@Component({
  selector: 'app-agregar-empleados',
  templateUrl: './agregar-empleados.component.html',
  styleUrls: ['./agregar-empleados.component.css']
})
export class AgregarEmpleadosComponent {
  esMayorDeEdad: boolean = false;
  formulario:FormGroup; // Formulario FormGroup
  estadoPeticion:String=""
  constructor(private fb: FormBuilder,private empleadoService:EmpleadosService, private route:ActivatedRoute,private router:Router,) {
    this.formulario = this.fb.group({
      nroDocumento: new FormControl ('', [Validators.required, Validators.pattern("^[0-9]+$")]),
      nombre: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z]+$")]),
      apellido:new FormControl ('', [Validators.required, Validators.pattern("^[a-zA-Z]+$")]),
      email: new FormControl('', [Validators.required, Validators.pattern("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")]),
      fechaNacimiento:new FormControl ('', [Validators.required, this.verificarEdad.bind(this)]),
      fechaIngreso:new FormControl ('', [Validators.required, this.verificarFechaIngreso.bind(this)]),
    });
  }
  verificarEdad(control:any) {
    const fechaNacimiento = new Date(control.value);
    const fechaActual = new Date();
    const edad = fechaActual.getFullYear() - fechaNacimiento.getFullYear();

    if (
      fechaActual.getMonth() < fechaNacimiento.getMonth() ||
      (fechaActual.getMonth() === fechaNacimiento.getMonth() &&
        fechaActual.getDate() < fechaNacimiento.getDate())
    ) {
      this.esMayorDeEdad = edad-1 >= 18;
    } else {
      this.esMayorDeEdad = edad >= 18;
    }

    return this.esMayorDeEdad ? null : { menorDeEdad: true };
  }
  verificarFechaIngreso(control:any) {
    const fechaIngreso = new Date(control.value);
    const fechaActual = new Date();


    if (fechaIngreso > fechaActual) {
      return { fechaIngresoPosterior: true };
    }

    return null;
  }

  agregarEmpleado(){
    this.empleadoService.agregarEmpleado(this.formulario).subscribe((data:any)=>{
        this.estadoPeticion=data.body
        console.log(data.statusCodeValue)
        this.router.navigate(['empleados'])
    }),((error:any) => {
      Swal.fire('Error ! ', 'No se ha podido actualizar el empleado','error');
      this.router.navigate(['empleados'])
    })

  }
  volver(){
    this.router.navigate(['empleados'])
  }
}
