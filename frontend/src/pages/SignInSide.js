import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import LoginImg from '../assets/images/react-folder-structure-1.png'
import { useNavigate } from 'react-router-dom';
import AuthService from '../services/auth-service';

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="">
        Test-Project
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

// ---------------login---------------------
export default function SignInSide() {

  const navigate = useNavigate();

  // -------------initial states for fields---------------------------
  const initialValues = { userName: "", password: "" };

  // ----------create state name form values--------
  const [formValues, setFormValues] = React.useState(initialValues);

  // -------------usestate for submit form-----------
  const [isSubmit, setIsSubmit] = React.useState(false);

  // -------function to handle changes in the input fields and set it to formvalues----------
  const handleChange = (e) => {

    // destructuring inputfield
    const { name, value } = e.target;
    // get the relavant name as key and assign value to it
    setFormValues({ ...formValues, [name]: value });

  }

  const handleSubmit = (event) => {
    event.preventDefault();
    setIsSubmit(true);
  };

  React.useEffect((event) => {

    const errors = {};

    if (isSubmit) {
      const userName = formValues.userName;
      const password = formValues.password;

      AuthService.login(userName, password).then(() => {

        navigate("/Test1/SellerDashBoard")


      }, (error) => {
        errors.NotFound = error.response.data;
        // setFormErrors(errors);
      })
    }

  })

  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${LoginImg})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth

                label="Username"
                name='userName'
                autoComplete='name'

                id="userName"
                value={formValues.userName}
                onChange={handleChange}
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                value={formValues.password}
                onChange={handleChange}
              />
              <FormControlLabel
                control={<Checkbox value="remember" color="primary" />}
                label="Remember me"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign In
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link href="#" variant="body2">
                    Forgot password?
                  </Link>
                </Grid>
                <Grid item>
                  <Link href="#" variant="body2">
                    {"Don't have an account? Sign Up"}
                  </Link>
                </Grid>
              </Grid>
              <Copyright sx={{ mt: 5 }} />
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}