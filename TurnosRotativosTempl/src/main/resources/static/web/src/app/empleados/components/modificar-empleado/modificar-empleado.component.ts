import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators,FormControl } from '@angular/forms';
import { EmpleadosService } from '../../empleados.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Empleado } from '../../entidad/empleado';
@Component({
  selector: 'app-modificar-empleado',
  templateUrl: './modificar-empleado.component.html',
  styleUrls: ['./modificar-empleado.component.css']
})
export class ModificarEmpleadoComponent {
  esMayorDeEdad: boolean = false;
  formulario: FormGroup; // Formulario FormGroup
  idEmpleado: number=this.route.snapshot.params['id'];
  empleado: Empleado = new Empleado();
  constructor(private fb: FormBuilder,private empleadoService:EmpleadosService, private route:ActivatedRoute,private router:Router,) {
    this.formulario = fb.group({
      nroDocumento: new FormControl ('', [Validators.required, Validators.pattern("^[a-zA-Z]+$")]),
      nombre: new FormControl('', [Validators.required, Validators.pattern("^[a-zA-Z]+$")]),
      apellido:new FormControl ('', [Validators.required, Validators.pattern("^[a-zA-Z]+$")]),
      email: new FormControl('', [Validators.required, Validators.pattern("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")]),
      fechaNacimiento:new FormControl ('', [Validators.required, this.verificarEdad.bind(this)]),
      fechaIngreso:new FormControl ('', [Validators.required, this.verificarFechaIngreso.bind(this)]),
    });
  }
  ngOnInit(){
    this.empleadoService.obtenerEmpleado(this.idEmpleado).subscribe((data:any)=>{
      this.empleado=data
    },(error) => this.router.navigate(['/error']) )
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
      this.esMayorDeEdad = edad >= 18;
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

    return null; // La fecha de ingreso es válida
  }
}
