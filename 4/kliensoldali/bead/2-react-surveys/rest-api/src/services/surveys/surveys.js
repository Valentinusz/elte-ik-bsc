// For more information about this file see https://dove.feathersjs.com/guides/cli/service.html
import { authenticate } from '@feathersjs/authentication'

import { hooks as schemaHooks } from '@feathersjs/schema'
import {
  surveyDataValidator,
  surveyPatchValidator,
  surveyQueryValidator,
  surveyResolver,
  surveyExternalResolver,
  surveyDataResolver,
  surveyPatchResolver,
  surveyQueryResolver
} from './surveys.schema.js'
import { SurveyService, getOptions } from './surveys.class.js'
import { checkQuery } from '../../hooks/check-query.js'

export const surveyPath = 'surveys'
export const surveyMethods = ['find', 'get', 'create', 'patch', 'remove']

export * from './surveys.class.js'
export * from './surveys.schema.js'

// A configure function that registers the service and its hooks via `app.configure`
export const survey = (app) => {
  // Register our service on the Feathers application
  app.use(surveyPath, new SurveyService(getOptions(app)), {
    // A list of all methods this service exposes externally
    methods: surveyMethods,
    // You can add additional custom events to be sent to clients here
    events: []
  })
  // Initialize hooks
  app.service(surveyPath).hooks({
    around: {
      all: [
        authenticate('jwt'),
        schemaHooks.resolveExternal(surveyExternalResolver),
        schemaHooks.resolveResult(surveyResolver)
      ]
    },
    before: {
      all: [schemaHooks.validateQuery(surveyQueryValidator), schemaHooks.resolveQuery(surveyQueryResolver)],
      find: [checkQuery],
      get: [checkQuery],
      create: [schemaHooks.validateData(surveyDataValidator), schemaHooks.resolveData(surveyDataResolver)],
      patch: [schemaHooks.validateData(surveyPatchValidator), schemaHooks.resolveData(surveyPatchResolver)],
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
