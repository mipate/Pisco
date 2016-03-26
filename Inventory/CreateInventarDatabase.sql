drop table if exists COMPONENTS;

drop table if exists SUPPLIERS;

drop table if exists PURCHASES;

create table COMPONENTS
(Part_No VARCHAR(8) Primary Key Not Null,
Description VARCHAR(200),
Location VARCHAR(100),
Qty Number(4,2));

create table SUPPLIERS
(Suppl_Code VARCHAR(8) Primary Key,
Description VARCHAR(100),
Address VARCHAR(200));

create table PURCHASES
(Id AUTONUMBER Primary Key,
Suppl_Code VARCHAR(8),
Part_No,
Date Date,
Invoice_no VARCHAR(8),
Qty Number(4,2),
Price Number(6,4),
CompCodeFromSuppl VARCHAR(8),
foreign key (Suppl_Code) references SUPPLIERS(Suppl_Code),  -- it is allowed even if the referenced table not exist
foreign key (Part_No) references COMPONENTS(Part_No));

--Insert some data

insert into COMPONENTS (Part_no, Description, Location, Qty)
values
('ASD1231OP', 'Component 1 Description', 'Component 1 Location', 23);


insert into COMPONENTS (Part_no, Description, Location, Qty)
values
('BSD1234OP', 'Component 2 Description', 'Component 2 Location', 45);


insert into COMPONENTS (Part_no, Description, Location, Qty)
values
('BAD1234OP', 'Component 3 Description', 'Component 3 Location', 56.89);

