import { NbMenuItem } from '@nebular/theme';

const FIRSTNAME_TABLE_LINK = '/pages/tables/firstnames';
const FIRSTNAME_TRANSLATION_TABLE_LINK = '/pages/tables/firstname-translations';
const LOGIN_LINK = '/auth/login';

export const MENU_ITEMS_ALL: NbMenuItem[] = [
  {
    title: 'Dashboard',
    icon: 'pie-chart-outline',
    link: '/pages/dashboard',
    home: true,
  },
  {
    title: 'DATABASE',
    group: true,
  },
  {
    title: 'Tables & Data',
    icon: 'grid-outline',
    children: [
      {
        title: 'Firstnames',
        link: FIRSTNAME_TABLE_LINK,
      },
      {
        title: 'Firstnames translations',
        link: FIRSTNAME_TRANSLATION_TABLE_LINK,
      },
    ],
  },
];

export const MENU_ITEMS_ADMIN: NbMenuItem[] = [
  {
    title: 'Dashboard',
    icon: 'pie-chart-outline',
    link: '/pages/dashboard',
    home: true,
  },
  {
    title: 'DATABASE',
    group: true,
  },
  {
    title: 'Tables & Data',
    icon: 'grid-outline',
    children: [
      {
        title: 'Firstnames',
        link: FIRSTNAME_TABLE_LINK,
      },
      {
        title: 'Firstnames translations',
        link: FIRSTNAME_TRANSLATION_TABLE_LINK,
      },
    ],
  },
];

export const MENU_ITEMS_MODERATOR: NbMenuItem[] = [
  {
    title: 'DATABASE',
    group: true,
  },
  {
    title: 'Tables & Data',
    icon: 'grid-outline',
    children: [
      {
        title: 'Firstnames',
        link: FIRSTNAME_TABLE_LINK,
      },
      {
        title: 'Firstnames translations',
        link: FIRSTNAME_TRANSLATION_TABLE_LINK,
      },
    ],
  },
  {
    title: 'AUTH',
    group: true,
  },
  {
    title: 'Auth',
    icon: 'lock-outline',
    children: [
      {
        title: 'Login',
        link: LOGIN_LINK,
      },
    ],
  },
];

export const MENU_ITEMS_NOT_LOGGED: NbMenuItem[] = [
  {
    title: 'AUTH',
    group: true,
  },
  {
    title: 'Auth',
    icon: 'lock-outline',
    children: [
      {
        title: 'Login',
        link: LOGIN_LINK,
      },
    ],
  },
];
