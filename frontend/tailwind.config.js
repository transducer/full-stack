module.exports = {
  content: [
    "./src/**/*.cljs",
  ],
  darkMode: 'media',
  theme: {
    extend: {},
  },
  variants: {
    extend: {},
  },
 plugins: [
    require('@tailwindcss/forms')
  ]
};
