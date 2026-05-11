import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 5,
  duration: '20s',
  thresholds: {
    http_req_duration: ['p(95)<500'],
  },
};

export default function () {
  const url = 'http://localhost:8080/deals/import';

  const payload = JSON.stringify([
    {
      dealUniqueId: `K6-${__VU}-${__ITER}`,
      fromCurrency: 'USD',
      toCurrency: 'EUR',
      dealTimestamp: '2023-01-01T12:00:00',
      amount: 100.0,
    },
  ]);

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(url, payload, params);

  check(res, {
    'status is 200': (r) => r.status === 200,
  });

  sleep(1);
}
