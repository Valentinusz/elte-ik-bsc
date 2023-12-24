export async function up(knex) {
  await knex.schema.createTable('surveys', (table) => {
    table.increments('id')
    table.string('name')
    table.text('content')
    table.string('hash')
    table.bigint('createdAt')
    table.bigint('userId').references('id').inTable('users')
  })
}

export async function down(knex) {
  await knex.schema.dropTable('surveys')
}
