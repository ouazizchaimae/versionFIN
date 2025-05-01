import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import StadeOperatoireAdminTabNavigation from '../../../../../../navigation/admin/referentiel/StadeOperatoireAdminTabNavigation';

const StadeOperatoireAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <StadeOperatoireAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default StadeOperatoireAdmin;
