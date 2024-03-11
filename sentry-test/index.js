// You can also use ESM `import * as Sentry from "@sentry/node"` instead of `require`
const Sentry = require("@sentry/node");
const express = require('express')
const BadRequestError = require("./bad_request.error");
const UnauthorizedError = require("./unauthorized.error");
const InternalError = require("./internal.error");
const port = 3000

const app = express();
Sentry.init({
    dsn: "https://13ecfc322614e5a78c11cfbf15a0b77e@o4506890508632064.ingest.us.sentry.io/4506890510139392",
    integrations: [
        // enable HTTP calls tracing
        new Sentry.Integrations.Http({ tracing: true }),
        // enable Express.js middleware tracing
        new Sentry.Integrations.Express({ app }),
    ],
    // Performance Monitoring
    tracesSampleRate: 1.0, //  Capture 100% of the transactions
});

// The request handler must be the first middleware on the app
app.use(Sentry.Handlers.requestHandler());

// TracingHandler creates a trace for every incoming request
app.use(Sentry.Handlers.tracingHandler());

// All your controllers should live here
app.get("/", function rootHandler(req, res) {
    res.end("Hello world!");
});


app.get("/bad-request", function mainHandler(req, res) {
    throw new BadRequestError("My first Sentry error!");
});

app.get("/debug-sentry", function mainHandler(req, res) {
    throw new InternalError("My first Sentry error!");
});

// Optional fallthrough error handler
app.use((err, req, res, next) =>{
    // set locals, only providing error in development
    if (err instanceof BadRequestError) {
        res.statusCode = 400;
        res.end("Bad request");
        Sentry.withScope(function (scope) {
            scope.setLevel("warning");
            Sentry.setTag("page_locale", "de-at");
            Sentry.captureException(err);
        });
    } else if (err instanceof UnauthorizedError) {
        res.statusCode = 404;
        res.end("UnauthorizedError");
    } else {
        res.statusCode = 500;
        res.end("InternalError");
        Sentry.withScope(function (scope) {
            scope.setLevel("error");
            Sentry.captureException(err);
        });
    }
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})