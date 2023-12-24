import { result } from './results/results.js'

import { survey } from './surveys/surveys.js'

import { user } from './users/users.js'

export const services = (app) => {
  app.configure(result)

  app.configure(survey)

  app.configure(user)

  // All services will be registered here
}
