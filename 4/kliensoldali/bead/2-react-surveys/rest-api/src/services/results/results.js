// For more information about this file see https://dove.feathersjs.com/guides/cli/service.html
import { authenticate } from '@feathersjs/authentication'

import { hooks as schemaHooks } from '@feathersjs/schema'
import {
  resultDataValidator,
  resultPatchValidator,
  resultQueryValidator,
  resultResolver,
  resultExternalResolver,
  resultDataResolver,
  resultPatchResolver,
  resultQueryResolver
} from './results.schema.js'
import { ResultService, getOptions } from './results.class.js'

export const resultPath = 'results'
export const resultMethods = ['find', 'get', 'create', 'patch', 'remove']

export * from './results.class.js'
export * from './results.schema.js'

// A configure function that registers the service and its hooks via `app.configure`
export const result = (app) => {
  // Register our service on the Feathers application
  app.use(resultPath, new ResultService(getOptions(app)), {
    // A list of all methods this service exposes externally
    methods: resultMethods,
    // You can add additional custom events to be sent to clients here
    events: []
  })
  // Initialize hooks
  app.service(resultPath).hooks({
    around: {
      all: [schemaHooks.resolveExternal(resultExternalResolver), schemaHooks.resolveResult(resultResolver)]
    },
    before: {
      all: [schemaHooks.validateQuery(resultQueryValidator), schemaHooks.resolveQuery(resultQueryResolver)],
      find: [authenticate('jwt')],
      get: [authenticate('jwt')],
      create: [schemaHooks.validateData(resultDataValidator), schemaHooks.resolveData(resultDataResolver)],
      patch: [schemaHooks.validateData(resultPatchValidator), schemaHooks.resolveData(resultPatchResolver)],
      remove: []
    },
    after: {
      all: []
    },
    error: {
      all: []
    }
  })
}
