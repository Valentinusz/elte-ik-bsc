export async function up(knex) {
  await knex.schema.createTable('results', (table) => {
    table.increments('id')
    table.text('content')
    table.bigint('createdAt')
    table.bigint('surveyId').references('id').inTable('surveys')
  })
}

export async function down(knex) {
  await knex.schema.dropTable('results')
}
