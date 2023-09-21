import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { EmpleadosService } from '../../empleados.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-lista-empleados',
  templateUrl: './lista-empleados.component.html',
  styleUrls: ['./lista-empleados.component.css'],
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule],
})

export class ListaEmpleadosComponent {
  displayedColumns:Set<string>=new Set();
  dataSource:any;
  empleados:any
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private empleadoService: EmpleadosService, private router:Router){

  }
  ngOnInit(): void {
    this.traerEmpleados()
}
  traerEmpleados(){
    this.empleadoService.traerEmpleados().subscribe((data:any)=>{
      this.empleados=data
      this.dataSource= new MatTableDataSource<any>(data)
      this.displayedColumns=this.generarColumnas(data);
      this.dataSource.paginator = this.paginator;
    },(error) => this.router.navigate(['/error']) )
  }
  generarColumnas(empleado:any){
    let listaClaves:Set<string>=new Set();
    for(let [key,value]of Object.entries(empleado[0])){
      listaClaves.add(key);
    }
    listaClaves.delete('fechaIngreso')
    listaClaves.delete('fechaCreacion')
    listaClaves.add('modificar')
    listaClaves.add('eliminar')
    return listaClaves
  }
  borrarEmpleado(id:number){
    this.empleadoService.borrarEmpleado(id).subscribe(data=>{
      this.traerEmpleados()
    },(error) => this.router.navigate(['/error']) );

  }
  modificarEmpleadoRedir(id:number){
    this.router.navigate(["modificarEmpleados",id]);
  }
  agregarEmpleadoRedir(){
    this.router.navigate(["agregarEmpleados"])
  }

}
