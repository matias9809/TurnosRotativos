import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import{HttpClientModule} from '@angular/common/http';
import { ListaEmpleadosComponent } from './empleados/components/lista-empleados/lista-empleados.component';
import { AgregarEmpleadosComponent } from './empleados/components/agregar-empleados/agregar-empleados.component';
import { ModificarEmpleadoComponent } from './empleados/components/modificar-empleado/modificar-empleado.component';
import { VerEmpleadoComponent } from './empleados/components/ver-empleado/ver-empleado.component';
import { ErrorComponent } from './empleados/components/error/error/error.component';


const routes: Routes = [{
  path:'empleados',
  component:ListaEmpleadosComponent
},
{
  path:'agregarEmpleados',
  component:AgregarEmpleadosComponent
},
{
  path:'modificarEmpleados',
  component:ModificarEmpleadoComponent
},
{
  path:'verEmpleado/:id',
  component:VerEmpleadoComponent
},
{
  path:'error',
  component:ErrorComponent

}];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    MatTableModule,
    MatPaginatorModule,
    HttpClientModule,

],
  exports: [RouterModule,
    MatTableModule,
    MatPaginatorModule,
    HttpClientModule,

  ]
})
export class AppRoutingModule { }
