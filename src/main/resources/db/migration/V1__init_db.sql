create table corporations
(
  createdat timestamp(6),
  updatedat timestamp(6),
  id        uuid not null
    primary key,
  name      varchar(255),
  type      varchar(255)
    constraint corporations_type_check
      check ((type)::text = ANY ((ARRAY ['EMITTER'::character varying, 'HANDLER'::character varying])::text[]))
);

alter table corporations
owner to folder_app_user;

create table corporateusers
(
  isactive       boolean,
  createdat      timestamp(6),
  lastloginat    timestamp(6),
  updatedat      timestamp(6),
  corporation_id uuid
    unique
    constraint fkrptu7iim0o1v1u654tmvs791g
      references corporations,
  id             uuid not null
    primary key,
  password       varchar(255),
  username       varchar(255)
);

alter table corporateusers
  owner to folder_app_user;

create table fileactions
(
  createdat      timestamp(6),
  datetime       timestamp(6),
  updatedat      timestamp(6),
  id             uuid not null
    primary key,
  performedby_id uuid
    unique
    constraint fk6itaqjcqn7bj0qicb1pnrq525
      references corporateusers,
  newstatus      varchar(255)
    constraint fileactions_newstatus_check
      check ((newstatus)::text = ANY
             ((ARRAY ['PENDING'::character varying, 'FILLED'::character varying, 'SIGNED'::character varying])::text[])),
  oldstatus      varchar(255)
    constraint fileactions_oldstatus_check
      check ((oldstatus)::text = ANY
             ((ARRAY ['PENDING'::character varying, 'FILLED'::character varying, 'SIGNED'::character varying])::text[]))
);

alter table fileactions
  owner to folder_app_user;

create table folders
(
  currency                  smallint
    constraint folders_currency_check
      check ((currency >= 0) AND (currency <= 5)),
  totalamount               numeric(38, 2),
  type                      smallint
    constraint folders_type_check
      check ((type >= 0) AND (type <= 1)),
  createdat                 timestamp(6),
  updatedat                 timestamp(6),
  createdby_id              uuid
    constraint fkscsgjl7iq53newqq3f42b5fyk
      references corporateusers,
  destinationcorporation_id uuid
    constraint fk9ef4bffca0w022poqnnu5pmq1
      references corporations,
  id                        uuid not null
    primary key
);

alter table folders
  owner to folder_app_user;

create table files
(
  createdat           timestamp(6),
  updatedat           timestamp(6),
  assignedhandler_id  uuid
    constraint fkb50gm1gl74jt0ldqy908jd2wc
      references corporations,
  associatedfolder_id uuid
    constraint fkj195ou55vbxtqeyjseq4x9u4y
      references folders,
  id                  uuid not null
    primary key,
  priority            varchar(255)
    constraint files_priority_check
      check ((priority)::text = ANY
             ((ARRAY ['LOW'::character varying, 'NORMAL'::character varying, 'HIGH'::character varying, 'URGENT'::character varying])::text[])),
  status              varchar(255)
    constraint files_status_check
      check ((status)::text = ANY
             ((ARRAY ['PENDING'::character varying, 'FILLED'::character varying, 'SIGNED'::character varying])::text[])),
  type                varchar(255)
    constraint files_type_check
      check ((type)::text = ANY
             ((ARRAY ['INVOICE'::character varying, 'CONTRACT'::character varying, 'CERTIFICATE'::character varying, 'REPORT'::character varying, 'LICENSE'::character varying, 'MANIFEST'::character varying, 'OTHER_DOCUMENT'::character varying])::text[]))
);

alter table files
  owner to folder_app_user;

create table files_fileactions
(
  file_id          uuid not null
    constraint fk9o1wjkhc2hsc23yt9bd53y8u2
      references files,
  actionhistory_id uuid not null
    unique
    constraint fklh1uxek71rwnlh24wgq0kx1ub
      references fileactions
);

alter table files_fileactions
  owner to folder_app_user;

create table folders_files
(
  folder_id uuid not null
    constraint fkkxvhs8wqs03dioawarenrseuc
      references folders,
  files_id  uuid not null
    unique
    constraint fk28oarhwhauapgachtq7xmbn4a
      references files
);

alter table folders_files
  owner to folder_app_user;

create table products
(
  quantity    integer,
  unitvalue   numeric(38, 2),
  createdat   timestamp(6),
  updatedat   timestamp(6),
  id          uuid not null
    primary key,
  designation varchar(255),
  nature      varchar(255)
    constraint products_nature_check
      check ((nature)::text = ANY
             ((ARRAY ['CHEMICAL'::character varying, 'PHARMACEUTICAL'::character varying, 'FOOD_AND_BEVERAGE'::character varying, 'ELECTRONICS'::character varying, 'TEXTILE'::character varying])::text[]))
);

alter table products
  owner to folder_app_user;

create table folders_products
(
  folder_id   uuid not null
    constraint fk6ivkt8uf6suojl9dm5uiowqho
      references folders,
  products_id uuid not null
    unique
    constraint fk4f4lj32h7kjuhhqr7xae6hv9g
      references products
);

alter table folders_products
  owner to folder_app_user;

create table userroles
(
  name        smallint
    constraint userroles_name_check
      check ((name >= 0) AND (name <= 3)),
  createdat   timestamp(6),
  updatedat   timestamp(6),
  id          uuid not null
    primary key,
  description varchar(255)
);

alter table userroles
  owner to folder_app_user;

create table corporateusers_userroles
(
  corporateuser_id uuid not null
    constraint fk9abhtuu228h9bv56ru5n33q0v
      references corporateusers,
  roles_id         uuid not null
    unique
    constraint fk4e8hu6mu2ywpn7mhgaxqpt75p
      references userroles
);

alter table corporateusers_userroles
  owner to folder_app_user;

