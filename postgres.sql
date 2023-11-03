CREATE TABLE todolist(
    todo_id SERIAL,
    todo VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Jakarta'),

    PRIMARY KEY (todo_id)
);

drop table todolist;

truncate table todolist;

select * from todolist;