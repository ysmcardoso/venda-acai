create table adicional (id_seq int identity not null, ds_desc varchar(255) not null, ds_tmp_prp int not null, ds_vr numeric(19,2) not null, primary key (id_seq));
create table adicional_pedido (id_seq int identity not null, ds_desc varchar(255) not null, ds_tmp_prp int not null, ds_vr numeric(19,2) not null, id_seq_ped int, primary key (id_seq));
create table pedido (id_seq int identity not null, id_cli varchar(255) not null, tmp_prp_tot int not null, vr_tot numeric(19,2) not null, id_seq_sab int not null, id_seq_tam int not null, primary key (id_seq));
create table sabor (id_seq int identity not null, ds_desc varchar(255) not null, ds_tmp_adc int not null, primary key (id_seq));
create table tamanho (id_seq int identity not null, ds_desc varchar(255) not null, ds_tam varchar(255) not null, ds_tmp_prp int not null, ds_vr numeric(19,2) not null, primary key (id_seq));
alter table adicional_pedido add constraint FKtf2c4tew3x9uqhxvhittqsuby foreign key (id_seq_ped) references pedido;
alter table pedido add constraint FKkdswg4hhwsq2bhl650wjp1jic foreign key (id_seq_sab) references sabor;
alter table pedido add constraint FKjq58y9xk6juahur8iapbf53a7 foreign key (id_seq_tam) references tamanho;