import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { FabricanteTableDataSource, FabricanteTableItem } from './fabricante-table-data-source';

@Component({
  selector: 'app-fabricante-table',
  templateUrl: './fabricante-table.component.html',
  styleUrls: ['./fabricante-table.component.css']
})
export class FabricanteTableComponent implements AfterViewInit,  OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<FabricanteTableItem>;
  dataSource: FabricanteTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name'];

  ngOnInit(): void {
    this.dataSource = new FabricanteTableDataSource();
  }
 
  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }
}
